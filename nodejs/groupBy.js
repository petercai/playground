import _ from 'lodash'
function groupArrayOfObjects(list, key) {
    return list.reduce(function(rv, x) {
      (rv[x[key]] = rv[x[key]] || []).push(x);
      return rv;
    }, {});
  };
  
  var people = [
      {sex:"Male", name:"Jeff"},
      {sex:"Female", name:"Megan"},
      {sex:"Male", name:"Taylor"},
      {sex:"Female", name:"Madison"}
  ];
  var groupedPeople=groupArrayOfObjects(people,"sex");
  console.log(JSON.stringify(_.values(groupedPeople),null,2))
//   console.log(groupedPeople.Male);//will be the Males 
//   console.log(groupedPeople.Female);//will be the Females