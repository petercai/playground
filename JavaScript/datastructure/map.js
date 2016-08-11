/**
 * http://usejsdoc.org/
 */
function print(info){
	console.log(info);
}

function Dictionary(){
	this.data = new Array();
	this.put = function(key, value){
		this.data[key] = value;
	}
	this.get = function(key){
		return this.data[key];
	}
	this.remove = function(key){
		delete this.data[key];
	}
	this.display= function(){
		for( var p in this.data)
			print(p+" -> "+this.data[p]);
	}
}

var pbook = new Dictionary();
pbook.put("Mike","123");
pbook.put("David", "345");
pbook.put("Cynthia", "456");
print("David's extension: " + pbook.get("David"));
pbook.remove("David");
pbook.display();