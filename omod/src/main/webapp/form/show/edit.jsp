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
