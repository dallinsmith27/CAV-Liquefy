## Meers Fault

In creating input files from original Fortran input files, coordinates were rounded
to five decimal places.

## USGS
* ID: `3200`
* Original file: `CEUScm2014.in`

Earthquake rate on the USGS Meers fault model is governed by a 4500 year (0.000222 yr⁻¹) recurrence rate that was also used in 2008.

## SSCn
* ID: `3200`
* Original file: `CEUScm-meers_2014.in`

* `mfd-map.json` contains the 5 magnitude branches explicitly combined with the model
  rates (Tables H-5.4-2 and H-5.4-3 in the CEUS-SSC HID)

Earthquake rate on the SSCn Meers fault model is governed by a logic tree of rates and magnitudes. The total weight-averaged rate is: 0.000647 yr⁻¹.

## References

* Petersen, M.D., et al., 2014, Documentation for the 2014 update of the United States
  national seismic hazard maps: U.S. Geological Survey Open-File Report 2014–1091, 243
  p., <https://dx.doi.org/10.3133/ofr20141091>.
* OFR 2014-1091 Errata, <https://github.com/usgs/nshmp-haz-fortran/wiki/Open-File-Errata>


## 2023 NSHM

Meers fault geometry was updated (provided by J. Thompson Jobe). USGS and SSCn will both use this updated geometry. For now, new geometry is in both the Meers.geojson and Meers (SSCn).geojson files, but if these files are identical, do we need both of them?
