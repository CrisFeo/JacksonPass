/* =========== Data Loader =========== */
var Loader = {
	
	/**
	 * Formulates a request for pattern blocks from the server.
	 */
	getBlocks: function() {
		//DEBUG - Load from a file
		$.ajax({
			type: "GET",
			url: "Create",
			dataType: "xml",
			success: Loader.parseBlocks
		});
	},
	
	
	/**
	 * Parses the result of a request for xml formated block data. 
	 * @param {Object} xml
	 */
	parseBlocks: function(categories) {
		//Parse each category
		$(categories).find('category').each(function() {
			//Parse the traits out of this category
			var cat = $(this);
			var traits = Loader.extractTraits(cat.find('traits'));
			var category = new Models.Category(traits);
			Controller.addCategory(category);
			
			//Parse each block in this category
			cat.find('blocks block').each(function() {
				var blk = $(this);
				var traits = Loader.extractTraits(blk.find('traits'));
				var block = new Models.Block(traits);
				Controller.addBlock(block);
				category.addBlock(block);
			});
		});
		
		//Redraw the categories
		Controller.redrawCategories();
	},
	
	
	/*
	 * Extract the traits from a <traits> xml object into a plain js
	 * object and return it.
	 */
	extractTraits: function(traitSet) {
		//Go through each trait in this block and extract it into an object
		var traits = {};
		$(traitSet).find("trait").each(function() {
			var trait = $(this);
			traits[trait.attr("name")] = trait.text();
		});
		return traits;
	}
	
}


//jQuery AutoExec
$(function() {
	Loader.getBlocks();
});
