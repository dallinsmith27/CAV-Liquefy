# New Madrid Seismic Zone (NMSZ)

The New Madrid source model consists of two primary logic tree branches, `USGS` and `SSCn`, as
detailed below. The New Madrid logic tree as a whole has the ID: `3099`.

## USGS

The USGS NMSZ source model consists of five fault realizations arrayed east to west
(epistemic uncertainty in location of the NMSZ), each of which is composed of three fault
sections arrayed north to south. Depending on rupture model, the three sections in each east
to west realization may rupture together or independently.

### Original cluster-out files

* `NMSZnocl.500.2014.in`
* `NMSZnocl.1000.2014.in`
* `NMSZnocl.50000.2014.in`

### Original cluster-in files

* `newmad2014.500.cluster.in`
* `newmad2014.750.cluster.in`
* `newmad2014.1000.cluster.in`
* `newmad2014.1500.cluster.in`
* `newmad2014.50000.cluster.in`

| Sections                   | West | Mid-West | Center | Mid-East | East |
|:-------------------------- |:----:|:--------:|:------:|:--------:|:----:|
| North                      | 3000 | 3010     | 3020   | 3030     | 3040 |
| Center                     | 3001 | 3011     | 3021   | 3031     | 3041 |
| South                      | 3002 | 3012     | 3022   | 3032     | 3042 |
| __Ruptures__               |      |          |        |          |      |
| cluster-out (all sections) | 3003 | 3013     | 3023   | 3033     | 3043 |
| __Clusters__               |      |          |        |          |      |
| cluster-in (all sections)  | 3004 | 3014     | 3024   | 3034     | 3044 |
| cluster-in (center-south)  | 3005 | 3015     | 3025   | 3035     | 3045 |

The USGS cluster-in model includes additive rate models (750-yr and 1500-yr) to accomodate
a branch wherein all three sections rupture every 750 years and the southern two sections
rupture together every 500 years. In this implementation, the R2 rate branch is used to either
replicate the 500-yr R1 branch (cluster-out) or accomodate 750-yr (cluster-in, all) or 1500-yr
(cluster-in, center-south only) branch. In the latter model, the other three rate branch values
are set to null and ignored.

## SSCn

The SSCn model consists of northern New Madrid faults, the Reelfoot thrust, and southern New
Madrid faults, each of which are modeled with two different geometric variants.

* Original cluster-out files: `NMFS_RFT.RLME.in`
* Original cluster-in files: `NMFS_RLME_clu.in`

| 2014/2018 Sections  | ID   | SSCn ID |
|:------------------- |:---- | ------- |
| Blytheville (south) | 3050 |         |
| Blytheville (north) | 3051 |         |
| Bootheel            | 3052 |         |
| North               | 3060 |         |
| North (extension)   | 3061 |         |
| Reelfoot (west)     | 3070 |         |
| Reelfoot (center)   | 3071 |         |
| Reelfoot (east)     | 3072 |         |
| __Ruptures__        |      |         |
| South (Blytheville) | 3053 [3050, 3051]       | BaBfz   |
| South (Bootheel)    | 3054 [3050, 3052]       | BaBl    |
| North (short)       | 3060                    | NmnS    |
| North (extended)    | 3062 [3060, 3061]       | NmnL    |
| Reelfoot (short)    | 3071                    | RftS    |
| Reelfoot (extended) | 3073 [3070, 3071, 3072] | RftL    |
| __Clusters__        |      |         |
| BaBl-RftS-NmnS      | 3080 |         |
| BaBl-RftL-NmnS      | 3081 |         |
| BaBl-RftS-NmnL      | 3082 |         | 
| BaBl-RftL-NmnL      | 3083 |         |
| BaBfz-RftS-NmnS     | 3084 |         |
| BaBfz-RftL-NmnS     | 3085 |         |
| BaBfz-RftS-NmnL     | 3086 |         |
| BaBfz-RftL-NmnL     | 3087 |         |

For the 2023 NSHM update, some of the section, rupture, and cluster names were updated based on input from J. Thompson Jobe. IDs remained unchanged.

| 2023 Sections  | ID   | SSCn ID |
|:------------------- |:---- | ------- |
| Axial (south)       | 3050 |         |
| Axial (north)       | 3051 |         |
| Bootheel            | 3052 |         |
| New Madrid (north)  | 3060 |         |
| Charleston Uplift   | 3061 |         |
| New Madrid (west)   | 3070 |         |
| Reelfoot (north)    | 3071 |         |
| Reelfoot (south)    | 3072 |         |
| __Ruptures__        |      |         |
| South (Axial)       | 3053 [3050, 3051]       | AxSAxN  |
| South (Bootheel)    | 3054 [3050, 3052]       | AxSBl   |
| North (short)       | 3060                    | NmnS    |
| North (extended)    | 3062 [3060, 3061]       | NmnL    |
| Reelfoot (short)    | 3071                    | RftS    |
 Reelfoot (extended)  | 3073 [3070, 3071, 3072] | RftL    |
 | __Clusters__        |      |         |
 | AxSBl-RftS-NmnS     | 3080 |         |
 | AxSBl-RftL-NmnS     | 3081 |         |
 | AxSBl-RftS-NmnL     | 3082 |         |
 | AxSBl-RftL-NmnL     | 3083 |         |
 | AxsAxN-RftS-NmnS    | 3084 |         |
 | AxSAxN-RftL-NmnS    | 3085 |         |
 | AxSAxN-RftS-NmnL    | 3086 |         |
 | AxSAxN-RftL-NmnL    | 3087 |         |

Currently, the SSCn rate tree is replicated in each cluster directory. If there are other
places where this happens, consider checking for rate-tree while descending source-tree, not
just at bottom as currently required.

Low rate branch of `cluster-out` model is scaled by 1.0074257425 in order for total rate to
match 0.000499 in XML files.

Low rate branch of `cluster-in` model is set to 0.000619802 instead of 0.00062 from SSCn HID
database poisson branch to match weight averaged rate 0.002657 in XML.
