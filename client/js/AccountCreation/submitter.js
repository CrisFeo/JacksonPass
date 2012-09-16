/* =========== Submission Handler =========== */

var Submission = {
	
	/**
	 * Gathers the information in the document and posts it to the account creation URL 
	 */
	postProfile: function() {
		$.ajax({
			type: "POST",
			url: "Account",
			processData: false,
			data: Submission.getAccountInfo(),
			headers: Submission.getInfoHeaders(),
			success: Submission.success,
			error: Submission.failed
		});
	},
	
	
	/**
	 * Called when an account was successfully created 
	 */
	success: function(e) {
		//TODO Redirect to the homepage
		alert("SUCCESS!");
	},
	
	
	/**
	 * Called when the request to create a new account has failed for some reason 
	 */
	failed: function() {
		//TODO Let the user know something broke
		alert("FAILURE!");
	},
	
	/**
	 * Returns a map of account data 
	 */
	getAccountInfo: function() {
		return {
			username: "Lankin",
			password: Submission.getPattern()
		};
	},
	
	
	/**
	 * Collect addition headers in a map 
	 */
	getInfoHeaders: function() {
		return {
			platform: navigator.platform,
			browser_name: $.uaMatch(navigator.userAgent).browser
		};
	},
	
	
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