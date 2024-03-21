# Active Crust Gridded Seismicity Sources

Spatial PDFs are based on two logic tree nodes, declustering method and smoothing kernel.
Recurrence rate is based on the full catalog rate of earthquakes and may include three
branches in the future.

Active crust grid source logic tree, weights given in square brackets:

| Declustering Model      | Smoothing      | Rate            |
|:------------------------|:---------------|:----------------|
| Gardner-Knopoff [0.33]  | Fixed [0.5]    | *Full-lo* [0.0]
|                         |                | Full [1.0]
|                         |                | *Full-hi* [0.0]
|                         | Adaptive [0.5] | ...
| Nearest Neighbor [0.34] | ...            | ...
| Reasenberg-1985 [0.33]  | ...            | ...

## Source IDs

* 4700 grid bounding feature
* 4701:4703 decluster branches
* 4710:4715 smoothing rupture sets
