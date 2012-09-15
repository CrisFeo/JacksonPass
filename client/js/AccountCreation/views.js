/* =========== Model Views =========== */
var Views = {};


/* ----- View for a block in a category ----- */
Views.Block = function(model) {
	this.model = model;
	this.elt = ich.CategoryBlock(model);
	
	this.elt.data("model", model);
}

/**
 * Draws this block in the provided parent element.
 * @param {Object} parent 
 */
Views.Block.prototype.redraw = function(parent) {
	parent.append(this.elt);
}


/* ----- View for a category ----- */
Views.Category = function(model) {
	this.model = model;
	this.elt = ich.Category(model);
	
	this.blockViews = [];
	
	this.elt.data("model", model);
}

/**
 * Adds a block view to this category view 
 */
Views.Category.prototype.addBlock = function(blockView) {
	this.blockViews.push(blockView);
}

/**
 * Draws this category (With all of its child blocks)
 * in the provided parent element. 
 * @param {Object} parent
 */
Views.Category.prototype.redraw = function(parent) {
	//Create views for all of this categories blocks
	var catElt = this.elt.find('.CategoryBlocks');
	var catModel = this.model;
	$.each(this.model.blocks, function() {
		this.block_color = catModel.block_color;
		var blockView = new Views.Block(this);
		blockView.redraw(catElt);
	});
	
	parent.append(this.elt);
	
	this.model.blocks
	this.elt.find('.CategoryBlocks')
}
