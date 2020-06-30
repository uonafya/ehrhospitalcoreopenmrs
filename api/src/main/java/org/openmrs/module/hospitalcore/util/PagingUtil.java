package org.openmrs.module.hospitalcore.util;

public class PagingUtil {
    public static final int DEFAULT_PAGE_SIZE = 50;

    private int currentPage;

    private int pageSize;

    private int total;

    private String link;

    private int patientId;

    public PagingUtil() {}

    public PagingUtil(String link, int pageSize) {
        this.currentPage = 1;
        this.pageSize = pageSize;
        this.total = 0;
        this.link = link;
    }

    public PagingUtil(Integer pageSize, Integer currentPage, int total) {
        this.pageSize = (pageSize != null) ? pageSize.intValue() : 50;
        this.total = total;
        this.currentPage = (currentPage == null || currentPage.intValue() > total) ? 1 : currentPage.intValue();
    }

    public PagingUtil(String link, Integer pageSize, Integer currentPage, int total) {
        this.pageSize = (pageSize != null) ? pageSize.intValue() : 50;
        this.total = total;
        this.currentPage = (currentPage == null || currentPage.intValue() > total) ? 1 : currentPage.intValue();
        this.link = link;
    }

    public PagingUtil(String link, Integer pageSize, Integer currentPage, int total, Integer patientId) {
        this.pageSize = (pageSize != null) ? pageSize.intValue() : 50;
        this.total = total;
        this.currentPage = (currentPage == null || currentPage.intValue() > total) ? 1 : currentPage.intValue();
        this.link = link;
        this.patientId = patientId.intValue();
    }

    public String getBaseLink() {
        if (this.link.indexOf("?") < 0)
            return String.valueOf(this.link) + "?";
        return String.valueOf(this.link) + "&";
    }

    public int getNumberOfPages() {
        if (this.total % this.pageSize == 0)
            return this.total / this.pageSize;
        return this.total / this.pageSize + 1;
    }

    public int getStartPage() {
        int startPage = 1;
        if (this.currentPage > 2) {
            startPage = this.currentPage - 2;
            if (getNumberOfPages() - startPage < 4) {
                startPage = getNumberOfPages() - 4;
                if (startPage <= 0)
                    startPage = 1;
            }
        }
        return startPage;
    }

    public int getStartPos() {
        return (this.currentPage <= 0) ? 0 : ((this.currentPage - 1) * this.pageSize);
    }

    public int getEndPos() {
        int endPos = getStartPos() + getPageSize() - 1;
        endPos = (endPos >= getTotal()) ? (getTotal() - 1) : endPos;
        return endPos;
    }

    public int getCurrentPage() {
        if (this.currentPage > this.total)
            this.currentPage = this.total;
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage > 0) {
            this.currentPage = currentPage;
        } else {
            this.currentPage = 1;
        }
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        } else {
            this.pageSize = 50;
        }
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPatientId() {
        return this.patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPrev() {
        if (this.currentPage - 1 > 1)
            return this.currentPage - 1;
        return 1;
    }

    public int getNext() {
        if (this.currentPage + 1 < getNumberOfPages())
            return this.currentPage + 1;
        return getNumberOfPages();
    }
}
