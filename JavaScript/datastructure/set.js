/**
 * http://usejsdoc.org/
 */
var o = console;

function Set(){
	this.data = [];
	this.add = function(item){
		if(this.data.indexOf(item)<0){
			this.data.push(item);
			return true;
		}
		else
			return false;
	}
	this.remove = function(item){
		var pos = this.data.indexOf(item);
		if( pos >=0 ){
			this.data.splice(pos,1);
			return true;
		}
		else
			return false;
	}
	this.show = function(){
		return this.data;
	}
	this.contains = function(item){
		return this.data.indexOf(item)>=0;
	}
	this.intersection = function(set){
		var self = this;
		var arr =  set.data.filter(function(v){
			return self.contains(v);
		});
		var result = new Set();
		result.data = arr;
		return result;
	}
	this.union = function(set){
		var res = new Set();
		set.data.forEach(function(v){
			res.add(v);
		});
		this.data.forEach(function(v){
			res.add(v);
		});
		return res;
	}
	this.size = function(){
		return this.data.length;
	}
	this.subset = function(set){
		if( set.size() < this.size()) return false;
		var self = this;
		return this.data.every(function(v){
			return set.contains(v);
		});

	}
	this.difference = function(set){
		var result = new Set();
		result.data = this.data.filter(function(v){
			return !set.contains(v);
		})
		return result;
	}
}


//var names = new Set();
//names.add("David");
//names.add("Jennifer");
//names.add("Cynthia");
//names.add("Mike");
//names.add("Raymond");
//if (names.add("Mike")) {
//	o.log("Mike added")
//}
//else {
//	o.log("Can't add Mike, must already be in set");
//}
//o.log(names.show());
//var removed = "Mike";
//if (names.remove(removed)) {
//	o.log(removed + " removed.");
//}
//else {
//	o.log(removed + " not removed.");
//
//}
//names.add("Clayton");
//o.log(names.show());
//removed = "Alisa";
//if (names.remove("Mike")) {
//	o.log(removed + " removed.");
//}
//else {
//	o.log(removed + " not removed.");
//}

//var cis = new Set();
//cis.add("Mike");
//cis.add("Clayton");
//cis.add("Jennifer");
//cis.add("Raymond");
//var dmp = new Set();
//dmp.add("Raymond");
//dmp.add("Cynthia");
//dmp.add("Jonathan");
//var it = new Set();
//it = cis.union(dmp);
//o.log(it.show());


//var it = new Set();
//it.add("Cynthia");
//it.add("Clayton");
//it.add("Jennifer");
//it.add("Danny");
//it.add("Jonathan");
//it.add("Terrill");
//it.add("Raymond");
//it.add("Mike");
//var dmp = new Set();
//dmp.add("Cynthia");
//dmp.add("Raymond");
//dmp.add("Jonathan");
//if (dmp.subset(it)) {
//	o.log("DMP is a subset of IT.");
//}
//else {
//	o.log("DMP is not a subset of IT.");
//}


var cis = new Set();
var it = new Set();
cis.add("Clayton");
cis.add("Jennifer");
cis.add("Danny");
it.add("Bryan");
it.add("Clayton");
it.add("Jennifer");
var diff = new Set();
diff = cis.difference(it);
o.log("[" + cis.show() + "] difference [" + it.show()
		+ "] -> [" + diff.show() + "]");