CKEDITOR.plugins.add( 'formtextarea', {
	init: function( editor ){
		editor.addCommand( 'insertformtextarea',{
			exec : function( editor ) {    						
				tb_show("testing", "selectObsPopup.form?type=textarea&modal=true&height=120&width=600");
			}
		});
		
		editor.ui.addButton( 'formtextarea', {
			label: 'Insert textarea',
			command: 'insertformtextarea',
			icon: this.path + 'images/textarea.png'
		});
	}
});