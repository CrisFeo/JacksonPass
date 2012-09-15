/* =========== View Controller =========== */

var Controller = {
	
	//Convenience References
	CategoryContainer: $('#CategoryContainer'),
	PasswordCanvas: $('#PasswordCanvas'),
	
	//Lists of views
	categories: [],
	blocks: [],
	
	
	/**
	 * Pushes a category into the display list 
	 * @param {Object} category
	 */
	addCategory: function(category) {
		Controller.categories.push(category);
	},
	
	
	/**
	 * Pushes a block into the display list 
	 * @param {Object} block
	 */
	addBlock: function(block) {
		Controller.blocks.push(block);
	},
	
	
	/**
	 * Redraws the categories in their container 
	 */
	redrawCategories: function() {
		//Clear the category container
		Controller.CategoryContainer.empty();
		//Draw the templates for each category (Recursively draws the Blocks too)
		$.each(Controller.categories, function() {
			Controller.CategoryContainer.append(ich.Category(this));
		});
	}

}
