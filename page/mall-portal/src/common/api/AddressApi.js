import areaData from 'common/area.js';

export function getAreaData() {
  let convertedData = areaData.map(item => {
    let province = {
      value: item.areaId,
      label: item.areaName,
    };

    const cityList = item.children.map(city => {
      return {
        value: city.areaId,
        label: city.areaName,
        children: city.children.map(area => {
          return {
            value: area.areaId,
            label: area.areaName
          }
        })
      }
    })

    province.children = cityList
    return province;
  })

  return convertedData;
}