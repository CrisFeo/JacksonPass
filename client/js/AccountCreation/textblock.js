/* =========== View Controller =========== */

var PasswordCanvas = {
	
	blocks: [],
	
	
	/**
	 * Called to initialize handlers on the password canvas when the page loads 
	 */	
	init: function() {
		//Make the canvas accept dragged blocks
		Controller.PasswordCanvas.droppable({
			accept: '.CategoryBlock',
			drop: PasswordCanvas.onDrop,
			over: PasswordCanvas.onOver,
			out: PasswordCanvas.onOut
		});
	},
	
	
	/**
	 * Callback for when a block is droppd on the canvas 
	 * @param {Object} event
	 * @param {Object} ui
	 */
	onDrop: function(event, ui) {
		
	},
	
	
	/**
	 * Callback for when a block is dragged over the canvas
	 * @param {Object} event
	 * @param {Object} ui
	 */
	onOver: function(event, ui) {
		console.log("OVER!");
	},
	
	
	/**
	 *  Callback for when a block is moved off the canvas
	 * @param {Object} event
	 * @param {Object} ui
	 */
	onOut: function(event, ui) {
		console.log("OUT!");
	},
	
	
	/**
	 * Remove all blocks from the canvas 
	 */
	clearBlocks: function() {
		//Remove all block views
		Controller.PasswordCanvas.empty();
		//Clear the internal record of blocks
		PasswordCanvas.blocks = [];
	}
	
}



//jQuery AutoExec
$(function() {
	PasswordCanvas.init();
});
