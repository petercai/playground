import _ from "lodash";
import fs from "fs";

let raw = fs.readFileSync("sai.json");
let json = JSON.parse(raw);
// console.log(json)
let sai = json.map((a) =>
  _.pick(a, [
    "recordId",
    "objectName",
    "fieldName",
    "fieldLabel",
    "commonValue",
    "countryValue",
    "isExcluded",
  ])
);
// console.log(sai)

// let groups = sai.reduce((a, c) => {
//   if (a.length === 0 || a[a.length-1].fieldLabel !== c.fieldLabel) {
//     a.push([c]);
//   } else {
//     a[a.length-1].push(c);
//   }
//   return a;
// }, []);
//
// console.log(JSON.stringify(groups,null,2))

let groups = _.groupBy(sai, "fieldLabel");
console.log(groups);
// console.log(_.values(groups))
let res = _.values(groups).flatMap((a) =>
  a.sort((x, y) => (x.isExcluded === y.isExcluded ? 0 : x.isExcluded ? 1 : -1))
);
console.log(res);
