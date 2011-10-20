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
<%@ include file="/WEB-INF/template/include.jsp" %>

<script type="text/javascript">
	jQuery(document).ready(function(){
		jQuery(".date").datepicker({yearRange:'c-100:c+100', dateFormat: 'dd/mm/yy', changeMonth: true, changeYear: true});	
		jQuery("#formContent").fillForm("${values}");
	});
	
	FORMPREVIEW = {
	
		/** SUBMIT THE FORM */
		submit: function(){
			if(jQuery("#formContent").validateForm()){
				jQuery("#formContent").ajaxSubmit({
					success: function(responseText, statusText, xhr){
						json = jQuery.parseJSON(responseText);
						if(json.status="success"){
							tb_remove();
						}
					}
				});	
			}			
		}
	}
</script>

<form id="formContent" method="post" action="${pageContext.request.contextPath}/module/hospitalcore/showForm.form">
	<input type="hidden" name="encounterId" value="${encounterId}"/>
	<input type="hidden" name="redirect" value="${redirect}"/>
	${form.content}
</form>

<input type="button" value="Save" onClick="FORMPREVIEW.submit();"/>
<input type="button" value="Cancel" onClick="tb_remove();"/>
