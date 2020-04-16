function groupArrayOfObjects(list, key) {
    return list.reduce(function(map, element) {
        (map[element[key]] = map[element[key]] || []).push(element);
      return map;
    }, {});
  };
  
  var people = [
      {sex:"Male", name:"Jeff"},
      {sex:"Female", name:"Megan"},
      {sex:"Male", name:"Taylor"},
      {sex:"Female", name:"Madison"}
  ];
  var groupedPeople=groupArrayOfObjects(people,"sex");
  console.log(JSON.stringify(Object.values(groupedPeople),null,2))
