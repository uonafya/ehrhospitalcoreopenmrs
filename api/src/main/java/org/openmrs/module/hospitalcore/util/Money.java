package org.openmrs.module.hospitalcore.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Currency;

public final class Money implements Comparable<Money>, Serializable {
  private BigDecimal fAmount;

  private final Currency fCurrency;

  private final RoundingMode fRounding;

  private static Currency DEFAULT_CURRENCY;

  private static RoundingMode DEFAULT_ROUNDING;

  private int fHashCode;

  private static final int HASH_SEED = 23;

  private static final int HASH_FACTOR = 37;

  private static final long serialVersionUID = 7526471155622776147L;

  public static final class MismatchedCurrencyException extends RuntimeException {
    MismatchedCurrencyException(String aMessage) {
      super(aMessage);
    }
  }

  public static void init(Currency aDefaultCurrency, RoundingMode aDefaultRounding) {
    DEFAULT_CURRENCY = aDefaultCurrency;
    DEFAULT_ROUNDING = aDefaultRounding;
  }

  public Money(BigDecimal aAmount, Currency aCurrency, RoundingMode aRoundingStyle) {
    this.fAmount = aAmount;
    this.fCurrency = aCurrency;
    this.fRounding = aRoundingStyle;
    validateState();
  }

  public Money(BigDecimal aAmount) {
    this(aAmount, DEFAULT_CURRENCY, DEFAULT_ROUNDING);
  }

  public Money(BigDecimal aAmount, Currency aCurrency) {
    this(aAmount, aCurrency, DEFAULT_ROUNDING);
  }

  public BigDecimal getAmount() {
    return this.fAmount;
  }

  public Currency getCurrency() {
    return this.fCurrency;
  }

  public RoundingMode getRoundingStyle() {
    return this.fRounding;
  }

  public boolean isSameCurrencyAs(Money aThat) {
    boolean result = false;
    if (aThat != null)
      result = this.fCurrency.equals(aThat.fCurrency);
    return result;
  }

  public boolean isPlus() {
    return (this.fAmount.compareTo(BigDecimal.ZERO) > 0);
  }

  public boolean isMinus() {
    return (this.fAmount.compareTo(BigDecimal.ZERO) < 0);
  }

  public boolean isZero() {
    return (this.fAmount.compareTo(BigDecimal.ZERO) == 0);
  }

  public Money plus(Money aThat) {
    checkCurrenciesMatch(aThat);
    return new Money(this.fAmount.add(aThat.fAmount), this.fCurrency, this.fRounding);
  }

  public Money minus(Money aThat) {
    checkCurrenciesMatch(aThat);
    return new Money(this.fAmount.subtract(aThat.fAmount), this.fCurrency, this.fRounding);
  }

  public static Money sum(Collection<Money> aMoneys, Currency aCurrencyIfEmpty) {
    Money sum = new Money(BigDecimal.ZERO, aCurrencyIfEmpty);
    for (Money money : aMoneys)
      sum = sum.plus(money);
    return sum;
  }

  public boolean eq(Money aThat) {
    checkCurrenciesMatch(aThat);
    return (compareAmount(aThat) == 0);
  }

  public boolean gt(Money aThat) {
    checkCurrenciesMatch(aThat);
    return (compareAmount(aThat) > 0);
  }

  public boolean gteq(Money aThat) {
    checkCurrenciesMatch(aThat);
    return (compareAmount(aThat) >= 0);
  }

  public boolean lt(Money aThat) {
    checkCurrenciesMatch(aThat);
    return (compareAmount(aThat) < 0);
  }

  public boolean lteq(Money aThat) {
    checkCurrenciesMatch(aThat);
    return (compareAmount(aThat) <= 0);
  }

  public Money times(int aFactor) {
    BigDecimal factor = new BigDecimal(aFactor);
    BigDecimal newAmount = this.fAmount.multiply(factor);
    return new Money(newAmount, this.fCurrency, this.fRounding);
  }

  public Money times(double aFactor) {
    BigDecimal newAmount = this.fAmount.multiply(asBigDecimal(aFactor));
    newAmount = newAmount.setScale(getNumDecimalsForCurrency(), this.fRounding);
    return new Money(newAmount, this.fCurrency, this.fRounding);
  }

  public Money div(int aDivisor) {
    BigDecimal divisor = new BigDecimal(aDivisor);
    BigDecimal newAmount = this.fAmount.divide(divisor, this.fRounding);
    return new Money(newAmount, this.fCurrency, this.fRounding);
  }

  public Money div(double aDivisor) {
    BigDecimal newAmount = this.fAmount.divide(asBigDecimal(aDivisor), this.fRounding);
    return new Money(newAmount, this.fCurrency, this.fRounding);
  }

  public Money abs() {
    return isPlus() ? this : times(-1);
  }

  public Money negate() {
    return times(-1);
  }

  public String toString() {
    return this.fAmount.toPlainString() + " " + this.fCurrency.getSymbol();
  }

  public boolean equals(Object aThat) {
    if (this == aThat)
      return true;
    if (!(aThat instanceof Money))
      return false;
    Money that = (Money)aThat;
    boolean result = this.fAmount.equals(that.fAmount);
    result = (result && this.fCurrency.equals(that.fCurrency));
    result = (result && this.fRounding == that.fRounding);
    return result;
  }

  public int hashCode() {
    if (this.fHashCode == 0) {
      this.fHashCode = 23;
      this.fHashCode = 37 * this.fHashCode + this.fAmount.hashCode();
      this.fHashCode = 37 * this.fHashCode + this.fCurrency.hashCode();
      this.fHashCode = 37 * this.fHashCode + this.fRounding.hashCode();
    }
    return this.fHashCode;
  }

  public int compareTo(Money aThat) {
    int EQUAL = 0;
    if (this == aThat)
      return 0;
    int comparison = this.fAmount.compareTo(aThat.fAmount);
    if (comparison != 0)
      return comparison;
    comparison = this.fCurrency.getCurrencyCode().compareTo(aThat.fCurrency.getCurrencyCode());
    if (comparison != 0)
      return comparison;
    comparison = this.fRounding.compareTo(aThat.fRounding);
    if (comparison != 0)
      return comparison;
    return 0;
  }

  private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
    aInputStream.defaultReadObject();
    this.fAmount = new BigDecimal(this.fAmount.toPlainString());
    validateState();
  }

  private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
    aOutputStream.defaultWriteObject();
  }

  private void validateState() {
    if (this.fAmount == null)
      throw new IllegalArgumentException("Amount cannot be null");
    if (this.fCurrency == null)
      throw new IllegalArgumentException("Currency cannot be null");
    if (this.fAmount.scale() > getNumDecimalsForCurrency())
      throw new IllegalArgumentException("Number of decimals is " + this.fAmount.scale() + ", but currency only takes " + getNumDecimalsForCurrency() + " decimals.");
  }

  private int getNumDecimalsForCurrency() {
    return this.fCurrency.getDefaultFractionDigits();
  }

  private void checkCurrenciesMatch(Money aThat) {
    if (!this.fCurrency.equals(aThat.getCurrency()))
      throw new MismatchedCurrencyException(aThat.getCurrency() + " doesn't match the expected currency : " + this.fCurrency);
  }

  private int compareAmount(Money aThat) {
    return this.fAmount.compareTo(aThat.fAmount);
  }

  private BigDecimal asBigDecimal(double aDouble) {
    String asString = Double.toString(aDouble);
    return new BigDecimal(asString);
  }
}
