/* =========== Submission Handler =========== */

var Submission = {
	
	/**
	 * Get the pattern representation of the password
	 */
	getPattern: function() {
		var pattern = "";
		
		Controller.PasswordCanvas.children().each(function() {
			var block = $(this);
			var value = (block.hasClass('TextBlock')) ? block.find('p.value').text(): block.data('model').pattern;
			pattern += value;
		});
		
		return pattern;
	}
	
}