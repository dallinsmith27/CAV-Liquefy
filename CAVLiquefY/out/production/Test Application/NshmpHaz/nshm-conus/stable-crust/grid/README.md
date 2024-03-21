# Stable Crust Grid Sources

Central & Eastern U.S grid (or smoothed seismicity) sources have IDs range: `8000-8099`. The
grid source logic tree as a whole has the ID: `8099`.

The features in the source model demarcate the seismotectonic regions of the central and
eastern U.S. Each zone has an associated 4-branch logic tree of maximum magnitude (mMax). These
zones are derived from the seimotectonic zones used in the original 2014 NSHM, but polygons with
identical MFDs have been consolidated and the default zone has been explicitely defined.

## 2008 IDs

IDs 8000-8010 are reserved for CEUS 2008 grids; TODO not sure yet if/how we'll split out `margin`
and `craton` and the two Mb conversion methods. Perhaps 2014 craton and extended margin for 2008
should be the same as for 2014.

2008 ID:8000 Mb Atkinson-Boore  
2008 ID:8001 Mb Johnson  

## 2014 IDs

* Numbers in brackets indicate the numeric zones assigned in the legacy fortran input files.
* The `default` zone covers everywhere outside the identified tectonic/geologic provinces.

### Polygon IDs

| Group | Geologic Region Name  | ID   |
|-------|-----------------------|------|
| USGS  | Craton [1,3,4]        | 8010 |
| USGS  | Extended Margin [2]   | 8011 |
| USGS  | default               | 8012 |
| SSCn  | Craton [1,9,10]       | 8013 |
| SSCn  | Gulf Coast [2,3]      | 8014 |
| SSCn  | Mesozoic [4,5]        | 8015 |
| SSCn  | Mesozoic [6]          | 8016 |
| SSCn  | Paleozoic [7]         | 8017 |
| SSCn  | Paleozoic [8]         | 8018 |
| SSCn  | default               | 8019 |

### Rupture set IDs

| Group | Geologic Region Name  | pdf   | ID   |
|-------|-----------------------|-------|------|
| USGS  | Craton [1,3,4]        | fixed | 8020 |
| USGS  | Extended Margin [2]   | fixed | 8021 |
| USGS  | default               | fixed | 8022 |
| SSCn  | Craton [1,9,10]       | fixed | 8023 |
| SSCn  | Gulf Coast [2,3]      | fixed | 8024 |
| SSCn  | Mesozoic [4,5]        | fixed | 8025 |
| SSCn  | Mesozoic [6]          | fixed | 8026 |
| SSCn  | Paleozoic [7]         | fixed | 8027 |
| SSCn  | Paleozoic [8]         | fixed | 8028 |
| SSCn  | default               | fixed | 8029 |
| USGS  | Craton [1,3,4]        | adapt | 8030 |
| USGS  | Extended Margin [2]   | adapt | 8031 |
| USGS  | default               | adapt | 8032 |
| SSCn  | Craton [1,9,10]       | adapt | 8033 |
| SSCn  | Gulf Coast [2,3]      | adapt | 8034 |
| SSCn  | Mesozoic [4,5]        | adapt | 8035 |
| SSCn  | Mesozoic [6]          | adapt | 8036 |
| SSCn  | Paleozoic [7]         | adapt | 8037 |
| SSCn  | Paleozoic [8]         | adapt | 8038 |
| SSCn  | default               | adapt | 8039 |

## 2023 Updates

 - Multiple declustering methods considered (gk, nn, r85)
 - Adaptive/fixed smoothing weights changed from 40/60 to 50/50, consistent with `active-crust`
 - rates being used in model are total catalog rates, not declustered rates.
 - TODO update 2023 ID's
