# Meers Fault

The Meers fault model combines the 2008 USGS and CEUS-SSCn models with equal weight (Figure 31,
[OFR 2014-1091](https://dx.doi.org/10.3133/ofr20141091)). The Meers fault source logic tree as
a whole has the ID: `3209`.

## USGS

* ID: `3200`
* Original file: `CEUScm2014.in`
* Comments: Only aleatory variability on full ruptures is enabled; full ruptures do not float.
* See also: [OFR 2014-1091 Errata](https://github.com/usgs/nshmp-haz-fortran/wiki/Open-File-Errata)

## SSCn

* ID: `3201`
* Original file: `CEUScm-meers_2014.in`
* Comments: Only aleatory variability on full ruptures is enabled; full ruptures do not float.

Low rate branch of `cluster-out` model is scaled by 2.47207 in order for total rate to match
0.000647 in XML files.

For the 2023 NSHM update, the Meers fault geometry was updated based on parameters provided by Jessica Thompson Jobe in fall 2021.