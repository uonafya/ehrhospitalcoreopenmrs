<%@ include file="/WEB-INF/template/include.jsp" %>

<script type="text/javascript">
	jQuery(document).ready(function(){
		jQuery(".date").datepicker({yearRange:'c-100:c+100', dateFormat: 'dd/mm/yy', changeMonth: true, changeYear: true});	
	});
	
	FORMPREVIEW = {
	
		validate: function(){
			if(jQuery("#formContent").validateForm()){
				tb_remove();	
			}			
		}
	}
</script>

<form id="formContent">
	${form.content}
</form>

<input type="button" value="Save" onClick="FORMPREVIEW.validate();"/>
<input type="button" value="Cancel" onClick="tb_remove();"/>
