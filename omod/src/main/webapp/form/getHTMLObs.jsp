 <%--
 *  Copyright 2009 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of Hospitalcore module.
 *
 *  Hospitalcore module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  Hospitalcore module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Hospitalcore module.  If not, see <http://www.gnu.org/licenses/>.
 *
--%> 
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:choose>
	<c:when test="${type eq 'text'}">
		<input type="text" name="${obsName}" value="" title="${obsName}" class="${required}"/>
	</c:when>
	<c:when test="${type eq 'textarea'}">
		<textarea name="${obsName}" rows="2" cols="20" class="${required}">
		</textarea>
	</c:when>
	<c:when test="${type eq 'number'}">
		<input type="text" name="${obsName}" value="" title="${obsName}" class="digit ${required}"/>
	</c:when>
	<c:when test="${type eq 'datetime'}">
		<input type="text" name="${obsName}" value="" title="${obsName}" class="date ${required}"/>
	</c:when>
	<c:when test="${type eq 'selection'}">
		<select name="${obsName}" title="${obsName}" class="${required}">
			<option value=''>Please select</option>
			<c:forEach var="option" items="${options}">
				<option value="${option}">${option}</option>
			</c:forEach>
		</select>
	</c:when>
	<c:when test="${type eq 'radio'}">
		<c:forEach var="option" items="${options}">
			<span><input type="radio" name="${obsName}" value="${option}" title="${obsName}" class="${required}"> ${option}</span>
		</c:forEach>
	</c:when>
</c:choose>