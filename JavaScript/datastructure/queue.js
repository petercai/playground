/**
 * http://usejsdoc.org/
 */
function Queue(){
	this.data = [];
	this.enqueue = function(element){
		this.data.push(element);
	}
	this.dequeue = function(element){
		return this.data.shift();
	}
	this.front = function(){
		return this.data[0];
	}
	this.back = function(){
		return this.data[this.data.length-1];
	}
}