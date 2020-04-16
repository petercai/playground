const groupBy = key => array =>
  array.reduce((objectsByKeyValue, obj) => {
    const value = obj[key];
    objectsByKeyValue[value] = (objectsByKeyValue[value] || []).concat(obj);
    return objectsByKeyValue;
  }, {});


  const groupBySlow = key => array =>
  array.reduce(
    (objectsByKeyValue, obj) => ({
      ...objectsByKeyValue,
      [obj[key]]: (objectsByKeyValue[obj[key]] || []).concat(obj)
    }),
    {}
  );


  const cars = [
    { brand: 'Audi', color: 'black' },
    { brand: 'Audi', color: 'white' },
    { brand: 'Ferarri', color: 'red' },
    { brand: 'Ford', color: 'white' },
    { brand: 'Peugot', color: 'white' }
  ];

  const groupByBrand = groupBy('brand');
  const groupByColor = groupBySlow('color');

  console.log(
    JSON.stringify({
      carsByBrand: groupByBrand(cars),
      carsByColor: groupByColor(cars)
    }, null, 2)
  );
