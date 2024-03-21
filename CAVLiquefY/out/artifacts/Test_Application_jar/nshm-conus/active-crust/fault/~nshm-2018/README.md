# WUS Finite Fault Sources

The MFD logic tree throughout most of the Western U.S. is ⅔ `full-rupture`
(or 'characteristic') and ⅓ `partial-rupture` (or Gutenberg-Richter). In the Pacific northwest,
this ratio changes to 50/50 as reflected in the `mfd-map.json` files in the `OR` and `WA`
directories.

## Notes on slip rates

In the postgres databases, slip rates for dipping faults are stored in mixed formats. Geologic
rates are generatlly vertical slip (or displacement) rates, but geodetically derived slip rates
are on-fault rates.

It is not clear if the fortran input files (from which all values in the legacy XML model format
files are copied) were all generated from the database, of if the geodetic input files were
generated separately with data being added back to the database after the fact. There _may_ have
been some loss of precision, whatever the process. In any event, vertical geologic slip rates
are converted to on-plane rates for the NSHM fault sections used in the 2014 and 2018 JSON format
source models.
