/* =========== Data Models =========== */
var Models = {};


/* ----- Model for a dynamic pattern block ----- */
Models.Block = function(blockData) {
	this.short_name = blockData.short_name;
	this.full_name  = blockData.full_name;
	this.pattern    = blockData.pattern;
}


/* ----- Model for a category of pattern blocks ----- */ 
Models.Category = function(catData) {
	this.name = catData.name;
	this.cat_color = catData.cat_color;
	this.block_color = catData.block_color;
	
	this.blocks = [];
}

/**
 * Add a block to this category 
 * @param {Object} block
 */
Models.Category.prototype.addBlock = function(block) {
	this.blocks.push(block);
}