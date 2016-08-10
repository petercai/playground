/**
 * http://usejsdoc.org/
 */
function print(info){
	console.log(info);
}

function Stack(){
	this.data = [];
	this.push = function(element){
		this.data.push(element);
	}
	this.pop = function(){
		return this.data.pop();
	}
	this.peek = function(){
		return this.data[this.data.length-1];
	}
	this.length = function(){
		return this.data.length;
	}
	this.clear = function(){
		this.data = [];
	}
}


var s = new Stack();
s.push("David");
s.push("Raymond");
s.push("Bryan");
print("length: " + s.length());
print(s.peek());
var popped = s.pop();
print("The popped element is: " + popped);
print(s.peek());
s.push("Cynthia");
print(s.peek());
s.clear();
print("length: " + s.length());
print(s.peek());
s.push("Clayton");
print(s.peek());