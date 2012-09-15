/* =========== Model Views =========== */
var Views = {};


/* ----- View for a block in a category ----- */
Views.Block = function(model) {
	this.model = model;
	this.elt = ich.CategoryBlock(model);
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
}

/**
 * Draws this category (With all of its child blocks)
 * in the provided parent element. 
 * @param {Object} parent
 */
Views.Category.prototype.redraw = function(parent) {
	parent.append(this.elt);
}
