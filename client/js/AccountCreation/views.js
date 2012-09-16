/* =========== Model Views =========== */
var Views = {};


/* ----- View for a plaintext block ----- */
Views.TextBlock = function() {
	this.elt = ich.CategoryBlock({block_color: "gray"})
		.addClass('TextBlock');
	this.textElt = $('<p>').addClass('value');
}

Views.TextBlock.prototype.enable = function(e) {
	var classRef = this;
	if (e && e.data.thisObj) {
		classRef = e.data.thisObj;
	}
	var text = classRef.textElt.html();
	var newText = $('<input>')
		.val(text)
		.attr('type', 'text')
		.width(50)
		.blur({thisObj: classRef}, classRef.disable)
		.keydown(function(e){
			//Enter keypress
			if (e.which==13) {
				newText.blur();
			}
		});
	classRef.textElt.remove();
	classRef.elt.find('.block_middle').append(newText);
	classRef.textElt = newText;
	newText.autoGrowInput({comfortZone: 10, minWidth:50}).focus();
}

Views.TextBlock.prototype.disable = function(e) {
	var classRef = this;
	if (e && e.data.thisObj) {
		classRef = e.data.thisObj;
	}
	var text = classRef.textElt.val();
	var newText = $('<p>')
		.addClass('value')
		.html(text)
		.click({thisObj: classRef}, classRef.enable);
	classRef.textElt.remove();
	classRef.elt.find('.block_middle').append(newText);
	classRef.textElt = newText;
}


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
