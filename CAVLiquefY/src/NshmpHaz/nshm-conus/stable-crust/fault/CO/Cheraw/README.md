# Cheraw Fault

The Cheraw fault model combines the 2008 USGS model and the CEUS-SSCn model with equal weights
(Figure 32, [OFR 2014-1091](https://dx.doi.org/10.3133/ofr20141091)). The Cheraw logic tree
as a whole has the ID: `2189`

## USGS

* ID: `2180`
* Original file: `CEUScm2014.in`
* Comments: Epistemic and aleatory are enabled; full rupture does not float.

The USGS model includes full (a.k.a. characteristic) and partial (floating Gutenberg–Richter)
rupture  models, equally weighted.

## SSCn

* ID: `2181`
* Original files: `CEUScm-srchar_2014.in`, `CEUScm-srgr_2014.in`, `CEUScm-recur_2014.in`
* Comments: Only aleatory variability on full ruptures is enabled; full ruptures do not float.

The SSCn model includes equally-weighted slip- and recurrence-rate branches. The slip rate branch
 includes full (a.k.a. characteristic) and partial (floating Gutenberg–Richter) rupture models,
 weighted ⅔ and ⅓, respectively. The earthquake recurrence branch includes in-temporal-cluster
 and out-of-temporal-cluster models; the rates and weights of these branches are combined into
 a single tree in `sscn/rate-tree.json` of 1-, 2-, and 3-earthquakes over different timespans
 (20-25k [2eq, 3eq] and 200-500k [1eq]),  years (R1, R2, R3).

Discrepancies between fault traces for the two MFD branches necessitated addition of a fault
section variant `Cheraw (SSCn, GR).geojson)` with ID `2182` that is slated for removal in a
future update.

Low rate branch of `recurrence-rate` model (1-eq) is scaled by 1.00387925805 in order for total
rate to match rate in XML files.
