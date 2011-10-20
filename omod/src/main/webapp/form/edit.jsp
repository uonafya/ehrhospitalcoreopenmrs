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
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<br/>
<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/hospitalcore/scripts/CKEditor/ckeditor.js"></script>

<script type="text/javascript">		
	var DUPLICATED_FORM = false;
	
	jQuery(document).ready(function(){		
		jQuery("#concept").autocomplete(openmrsContextPath + '/module/hospitalcore/ajax/autocompleteConceptSearch.htm').result(function(event, item){
			checkExistingForm();
		});;		
	});
	
	// insert obs from thickbox
	function insertObs(name, type){		
		jQuery.ajax({
			type : "GET",
			url : openmrsContextPath + "/module/hospitalcore/getHTMLObs.form",
			data : ({
				name			: name,
				type			: type
			}),
			success : function(data) {
				CKEDITOR.instances.editor1.insertHtml(data);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("ERROR " + xhr);
			}
		});		
	}
	
	// check existing form with concept/type
	function checkExistingForm(item){		
		type = jQuery('#formType').val();
		conceptName = jQuery("#concept").val();
		jQuery.ajax({
			type : "GET",
			url : openmrsContextPath + "/module/hospitalcore/ajax/checkExistingForm.htm",
			data : ({
				conceptName		: conceptName,
				type			: type,
				formId			: '${param.id}'
			}),
			success : function(data) {
				jQuery('#checkExistingFormStatus').html(data);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("ERROR " + xhr);
			}
		});			
	}
	
	// validate all data before submitting
	function submitForm(){		
		if(DUPLICATED_FORM){
			alert('Please check form type and concept and submit again!');			
		} else {
			jQuery("#coreForm").submit();
		}
	}
</script>

<form id='coreForm' method="post" enctype="multipart/form-data">		 
	<table>
		<tr>
			<spring:bind path="form.name">
				<td>Name</td>
				<td><input type="text" name="${status.expression}" value="${status.value}" style="width:350px;"/></td>
			</spring:bind>
		</tr>
		<tr>
			<spring:bind path="form.description">
				<td>Description</td>
				<td><input type="text" name="${status.expression}" value="${status.value}" style="width:350px;"/></td>
			</spring:bind>
		</tr>
	</table>
	<spring:bind path="form.content">
		<textarea class="ckeditor" cols="80" id="editor1" name="${status.expression}" rows="10">
			${status.value}
		</textarea>
	</spring:bind>
	<input type="button" value="Save" onClick="submitForm();"/>	
	<input type="button" value="Cancel" onClick="javascript:window.location.href='listForm.form'"/>	
</form>



<%@ include file="/WEB-INF/template/footer.jsp" %>  