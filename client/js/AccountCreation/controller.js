/* =========== View Controller =========== */

var Controller = {
	
	//Convenience References
	CategoryContainer: $('#CategoryContainer'),
	PasswordCanvas: $('#PasswordCanvas'),
	
	//Lists of views
	categories: [],
	blocks: [],
	
	
	/**
	 * Called to initialize handlers on static page elements
	 */
	init: function() {
		//Make category blocks sortable and connected to this
		Controller.PasswordCanvas.sortable({
			helper: 'clone',
			axis: false,
			beforeStop: function(event, ui) {
				if (!Util.overlaps(ui.helper, Controller.PasswordCanvas)) {
					//Delete the block
					ui.helper.remove();
					ui.item.remove();
				}
			}
		}).disableSelection();
		
		//Set up handling of static text entry blocks
		Controller.PasswordCanvas.click(Controller.addTextBlock);
	},
	
	
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
	
	
	addTextBlock: function(e) {
		if (e.target==Controller.PasswordCanvas[0]) {
			var block = new Views.TextBlock();
			$('#PasswordCanvas').append(block.elt);
			block.enable();
		}
	},
	
	
	/**
	 * Redraws the categories in their container 
	 */
	redrawCategories: function() {
		//Clear the category container
		Controller.CategoryContainer.empty();
		
		//Draw the templates for each category (Recursively draws the Blocks too)
		$.each(Controller.categories, function() {
			var catView = new Views.Category(this);
			catView.redraw(Controller.CategoryContainer);
		});
		
		//Make blocks Draggable
		$('.CategoryBlocks').sortable({
			helper: 'clone',
			connectWith: '#PasswordCanvas',
			placeholder: 'hide',
			start: function(event, ui) {
				$(ui.item).show();
				clone    = $(ui.item).clone();
				model    = $(ui.item).data("model");
				before   = $(ui.item).prev();
				position = $(ui.item).index();
				oldList  = $(ui.item).parent();
				clone.data("model", model);
				ui.helper.html(model.short_name);
				ui.helper.width('auto');
			},
			beforeStop: function(event, ui) {
				if($(ui.item).closest(oldList).length>0)
				$(this).sortable('cancel');
			},
			stop: function(event, ui) {
				if (position == 0) oldList.prepend(clone);
				else before.after(clone);
				ui.item.html(model.short_name);
			},
			cursor: "move"
		}).disableSelection();
	}

}



//jQuery AutoExec
$(function() {
	Controller.init();
});

