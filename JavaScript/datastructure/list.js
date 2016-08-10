/**
 * http://usejsdoc.org/
 */

function print(info){
	console.log(info);
}

function List(){
	this.data = [];
	this.size = 0;
	this.append =	function (element){
		this.data[this.size++]=element;
	}
	this.toString = function(){
		return this.data;
	}

	this.insert = function(element, after){
		var pos = this.find(after);
		if( pos > -1){
			this.data.splice(pos+1,0,element);
			return true;
		}
		return false;

	}
	this.find  = function(element){
		return this.data.indexOf(element);
	}

	this.contains = function(element){
		if( this.data.indexOf(element)>-1)
			return true;
		else
			return false;
	}
}





var list = new List();
list.append("Hello");
list.append("World");

print(list.toString());

print(list.find("World"));

list.insert("Peter", "World");

print(list.toString());
print(list.contains("Peter"));