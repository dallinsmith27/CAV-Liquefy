# Southern Whidbey Island

The Southern Whidbey Island fault model consists of three strands (`south`, `middle`, `north`),
each with two length variants. The longer versions, formerly identified with `Alt2` in their
name, were added in the 2014 NSHM update.

The 'characteristic' magnitudes of the longer strands are capped at **M**7.5, the regional
maximum magnitude. The shorter strands have magnitudes that do not agree with Wells & Coppersmith
(1994) lengths computed from the traces as defined in the 2008 and 2014 NSHMs; the magnitudes
correspond to lengths over 100km but the computed lengths are only on the order of ~90 km. The
Southern Whidbey Island rate model consists of slip rate partitioned over the three strands and
contains imbalanced geologic and geodetic branches. In the 2014 XML model, the geodetic
branches of the `middle` strand have been assigned double weight. More details can be found in
this [GitHub issue](https://github.com/usgs/nshmp-haz-fortran/issues/28).

## Notes for 2018 JSON model

The Southern Whidbey Island fault model is not a well defined logic tree. We preserve the
trace variants as `original` and `extended` branches. Because we can't support overweighting (2x)
of the geodetic branches, their rates have been halved in this updated model. Moreover, weights
in the 2014 model don't sum exactly to one, so `SINGLE` MFD branch rates have been scaled by
`1.0005` and `GR` branch rates by `0.999` to preserve an exact match with prior NSHMs.

## Source Tree and Feature IDs

| Source Name                      | Tree ID | Feature ID |
|----------------------------------|:-------:|:----------:|
| Southern Whidbey Island (south)  | 2896    |            |
| &nbsp;&nbsp;original             |         | 2890       |
| &nbsp;&nbsp;extended             |         | 2893       |
| Southern Whidbey Island (south)  | 2897    |            |
| &nbsp;&nbsp;original             |         | 2891       |
| &nbsp;&nbsp;extended             |         | 2894       |
| Southern Whidbey Island (south)  | 2898    |            |
| &nbsp;&nbsp;original             |         | 2892       |
| &nbsp;&nbsp;extended             |         | 2895       |
