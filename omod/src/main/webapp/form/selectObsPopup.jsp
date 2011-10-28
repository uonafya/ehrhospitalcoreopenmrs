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
		jQuery("#conceptPopup").autocomplete(openmrsContextPath + '/module/hospitalcore/ajax/autocompleteConceptSearch.htm').result(function(event, item){
			SELECTOBSPOPUP.showConceptInfo();
		});
		jQuery("#conceptPopup").focus();
	});
	
	SELECTOBSPOPUP = {
		
		// Get concept information and display on popup
		showConceptInfo: function(){
			
			jQuery.ajax({
				type : "GET",
				url : openmrsContextPath + "/module/hospitalcore/getConceptInfo.form",
				data : ({
					name: jQuery('#conceptPopup').val()
				}),
				dataType: "json",
				success : function(data) {
					jQuery("#title").val(data.description);
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert(thrownError);
				}
			});		
		},
		
		// Insert obs
		insert: function(){
			EDIT.insertObs(jQuery('#conceptPopup').val(), '${type}', jQuery('#required').is(':checked')); 
			tb_remove();
		}
	}
</script>

<center>
	<table>
		<tr>
			<td>
				<b>Concept</b>
			</td>
			<td colspan='4'>
				<input id="conceptPopup" style="width:350px;"/>
			</td>
		</tr>
		<tr>			
			<td>
				<c:if test="${type eq 'selection'}">
					<input type="checkbox" name="sex" id="multiple" /><label for="multiple"> Multiple selection</label>
				</c:if>
			</td>
			<td colspan='4'></td>
		</tr>
		<tr>
			<td valign='top'><b>Hint</b></td>
			<td colspan='4'>
				<textarea id="title" style="width:350px; height: 50px;"></textarea>
			</td>
		</tr>
		<tr>
			<td><b>Validation</b></td>
			<td>
				<input type="checkbox" id="validation_mandatory" checked="checked"/><label for="validation_required"> Mandatory</label>
			</td>
			<td>
				<input type="checkbox" id="validation_number"/><label for="validation_number"> Number</label>
			</td>			
			<td>
				<input type="checkbox" id="validation_digit"/><label for="validation_digit"> Digit</label>
			</td>
			<td>
				<input type="checkbox" id="validation_date"/><label for="validation_date"> Date</label>
			</td>
		</tr>
		<tr>
			<td><b>Custom validation</b></td>
			<td colspan='4'>
				<input id="validation_customized"/>
			</td>
		</tr>
	</table>	
	
	<input type="button" onClick="SELECTOBSPOPUP.insert();" value="Insert"/>
	<input type="button" onClick="tb_remove();" value="Close"/>
</center>