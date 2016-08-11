/**
 * http://usejsdoc.org/
 */
var o = console;

function Hashtable(){
	this.table = new Array(137); // why 137
	this.hash = function(data){
		var  hash = 0;
		Array.prototype.forEach.call(data, function(v){
			hash += v.charCodeAt();
		});

		return hash % this.table.length;
	};
	this.hash2 = function(data){
		const H = 37;
		var value = 0;
		Array.prototype.forEach.call(data, function(v){
			value += H*value +v.charCodeAt();
		});
		value = value%this.table.length;
		return parseInt(value);
	}
	this.put = function(data){
		var pos= this.hash2(data);
		this.table[pos] = data;
	}

	this.display = function(){
		var n = 0;
		this.table.forEach(function(v, i){
			if( v!=undefined)
				o.log(i+": "+v);
		});
	}
}

var someNames = ["David", "Jennifer", "Donnie", "Raymond",
                 "Cynthia", "Mike", "Clayton", "Danny", "Jonathan"];
var hTable = new Hashtable();
for (var i = 0; i < someNames.length; ++i) {
   hTable.put(someNames[i]);
}
//hTable.display();

function randomInt( min, max){
	return Math.floor(Math.random()*(max-min+1))+min;
}

function studendData(arr){
	for( var i=0;i<arr.length;i++){
		var num = "";
		for( var j=1;j<=9;j++)
			num += Math.floor(Math.random()*10);
		num += randomInt(50,100);
		arr[i] = num;
	}
}

var numStudents = 10;
var arrSize = 97;
var idLen = 9;
var students = new Array(numStudents);
studendData(students);
o.log ("Student data: \n");
for (var i = 0; i < students.length; ++i) {
   o.log(students[i].substring(0,8) + " " +
         students[i].substring(9));
}
o.log("\n\nData distribution: \n");
var hTable = new Hashtable();
for (var i = 0; i < students.length; ++i) {
   hTable.put(students[i]);
}
hTable.display();