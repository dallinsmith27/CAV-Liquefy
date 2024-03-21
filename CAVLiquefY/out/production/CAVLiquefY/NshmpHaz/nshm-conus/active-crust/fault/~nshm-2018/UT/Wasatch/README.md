# Wasatch Fault Zone

The Wasatch fault zone is modeled using `segmented` (90%) and `unsegmented` (10%; floater over
entire length of fault) sources. On the segmented branch, full (a.k.a. 'characteristic') ruptures
get 80% weight and partial (a.k.a. Gutenberg-Richter) ruptures get 20% weight, consistent with
weights assigned in the 2002 NSHM but different than that used for other normal faults in the
Western U.S (66.7% and 33.3%). The Wasatch logic tree as a whole has the ID: `2799`.

The complexity of the Wasatch model necessitates using a logic tree of rupture-sets with
explicitely defined MFDs and dip variant branches. It is not possible to derive MFDs from section
slip rates as is done for most other faults in the Western U.S.

## Fault Sections (2008/2014/2018)

| Section Name     | ID   |
| ---------------- | :--: |
| Brigham City     | 2780 |
| Weber            | 2781 |
| Salt Lake City   | 2782 |
| Provo            | 2783 |
| Nephi            | 2784 |
| Levan            | 2785 |
| All              | 2798 |

## Salt Lake City Section (2014/2018)

Four geometries:

- Model 0: Low resolution trace that is used on geodetic branches that is consistent with the
  2008 NSHM.
- Model 1: North, tear, and south sections for the `cluster` model
- Model 2: North, tear, and south sections joined in the `connected` model (referred to in
  legacy input files as 'Pechmann').
- Model 3: Similar to model 2 but with the south end of north section cut off (referred to
  in legacy input files as 'SLC through Virginia St')

The traces of models 0, 1, 2, and 3 are not identical and are listed in the table below.

| Section Name                    | ID   | Notes |
| ------------------------------- | :--: | ----- |
| Salt Lake City (geodetic)       | 2782 | 2014 geodetic models only |
| Salt Lake City (geologic)       | 2788 | a.k.a. SLC through Virginia St. |
| Salt Lake City (connected)      | 2795 | Pechmann connected tear geometry |
| Salt Lake City (cluster, north) | 2792 | |
| Salt Lake City (cluster, tear)  | 2794 | |
| Salt Lake City (cluster, south) | 2793 | EQGDB: [2782, 2793] |

The cluster-set ID for the Salt Lake City section cluster model is 2796.

## Scaled logic tree branches

The Wasatch model includes a number of 'scaled' logic tree branches. Low precision weights and
separation of model branches across different files in prior models necessitates the application
of a 1.0005 scale factor to `full-rupture` branches and 0.999 scale factor to `partial-rupture`
branches.

## Notes

The following is a list of curiosities, notes, and modifications made to ensure consistency with
the prior 2014/2018 NSHMs (XML format) and nshmp-haz (v1.*) codes:

- Brigham City, Provo, and Nephi sections use geologic rate on geodetic branches despite
  apparantly different slip rates in legacy fault Postgres database.
- Weber geodetic models have identical rates and M, but geologic M is different, M 7.16 vs 7.15.
- `wasatch-slc-partial-geologic` mfd-tree magnitude adjusted to 7.051 (from 7.51); dMag 0.0917
  doesn't divide nicely into SLC geologic magnitude range and upper bin gets gut off in new code,
  both when creating MFDs and computing moment rate for balancing epistemic branches.
- Equal high precision 1/3 weights of 0.333333 on geologic full-rupture branches reproduce results
  of XML and do not run afoul of 0.9999 sum constraint on logic tree weights.
- New codes require us to explicitely turn off epistemic and aleatory uncertainty for cluster
  branch.
- Wasatch SLC connected was modeled in a separate file and does not need the 1.0005 full-rupture
  scaling; therefore it includes a 'scaled' branch with 0.99951 scale factor that brings model
  in line with XML.
- The 2014 open-file report states the unsegmented model is a M7.4 (from Utah Fault Working Group)
  floater, consistent with prior models, however it actually uses a M6.5-7.4 Gutenberg-Richter MFD
  for a range of floating rupture magnitudes.
- Slip rates in the `unsegmented` (full, floating rupture model) are computed as the length
  weighted average of the slip rates reported for the individual sections. However, explicit
  a-values derived from prior implementations of the `unsegmented` model override those that
  would be derived from slip rate. It's not clear in Petersen et al., (2014) how the `unsegmented`
  a-values were computed.
- Wasatch SLC cluster model 'tear' fault was assigned a nominal slip rate of 0.01.
  
## EQGDB Sections

For reference, the table below lists the section IDs that will be in use in future model updates.

| Section                           | ID   |
| --------------------------------- | :--: |
| Malad City                        | 2229 |
| Clarkston Mountain                | 2778 |
| Collinston                        | 2789 |
| Brigham City                      | 2780 |
| Weber                             | 2781 |
| Salt Lake City (north)            | 2792 |
| Salt Lake City (Virginia Street)  | 2788 |
| Salt Lake City (Foothills)        | 2791 |
| Salt Lake City (East Bench)       | 2782 |
| Salt Lake City (south)            | 2793 |
| Provo (north)                     | 2783 |
| Provo (south)                     | 2790 |
| Nephi (north)                     | 2787 |
| Nephi (south)                     | 2784 |
| Levan                             | 2785 |
| Fayette                           | 2786 |
