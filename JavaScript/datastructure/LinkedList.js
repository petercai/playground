/**
 * http://usejsdoc.org/
 */

//function print(info){
//	console.log(info);
//}

var o = console;

function Node(element){
	this.e = element;
	this.next = null;
}

function LinkedList(){
	this.head = null;
	this.tail = null;
	this.empty = function(){
		return this.head==null&&this.tail==null;
	}
	this.find = function(item){
		var cur = this.head;
		while(cur!=null && cur.e != item )
			cur = cur.next;
		return cur;
	}
	this.addFirst = function(element){
		var item = new Node(element);
		if( this.empty()){
			this.head = item;
			this.tail = item;
		}
		else{
			item.next = this.head;
			this.head = item;
		}

	}
	this.addLast = function(element){
		var item = new Node(element);
		if(this.empty()){
			this.head = item;
			this.tail = item;
		}
		else{
			this.tail.next = item;
		}

	}
	this.insert = function(element, after){
		var item = new Node(element);
		var current= this.find(after);
		if( current != null ){
			item.next = current.next;
			current.next = item;
		}
		else{
			throw new Error("no such element");

		}
	}
	this.remove = function(element){

	}
	this.display = function(){
		var cur = this.head;
		while(cur.next!=null){
			o.log(cur.next.e);
			cur = cur.next;
		}2
	}
}


var cities = new LinkedList();
cities.addFirst("head");
cities.insert("Conway", "head");
cities.insert("Russellville", "Conway");
cities.insert("Alma", "Russellville");
try{

cities.insert("Error", "None");
}
catch(e){
	o.log(typeof e);
	if( e instanceof Error)
		o.log(e);
	else
		o.log("No type: "+e);
}
cities.display();
