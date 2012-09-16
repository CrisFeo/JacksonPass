/* =========== Utility Methods =========== */

var Util = {
	
	/**
	 *  Checks whether the two elements overlap
	 * @param {Object} a
	 * @param {Object} b
	 */
	overlaps: function(eltA, eltB) {
		oA = {
			x1: eltA.offset().left,
			y1: eltA.offset().top
		};
		oA.x2 = oA.x1 + eltA.width();
		oA.y2 = oA.y1 + eltA.height();
		
		oB = {
			x1: eltB.offset().left,
			y1: eltB.offset().top
		};
		oB.x2 = oB.x1 + eltB.width();
		oB.y2 = oB.y1 + eltB.height();
		
		var xCol = Util.valInRange(oA.x1, oB.x1, oB.x2) || 
					Util.valInRange(oB.x1, oA.x1, oA.x2);
		var yCol = Util.valInRange(oA.y1, oB.y1, oB.y2) || 
					Util.valInRange(oB.y1, oA.y1, oA.y2);
		
		return xCol && yCol;
	},
	
	
	valInRange: function(val, min, max) {
		return (val>=min) && (val<=max);
	}
	
}
