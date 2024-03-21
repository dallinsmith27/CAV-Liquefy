# NSHM: Conterminous U.S.

National Seismic Hazard Model (NSHM) for the conterminous U.S. This model is intended for use with
U.S. Geological Survey (USGS) hazard software [*nshmp-haz*][1].

For details on the format of this model, see the *nshmp-haz*
[user guide and implementation reference][2].

## Model Editions

* For prior models, see the [USGS Seismic Hazard Maps][3] web page.
* Current edition: 2018, version [5.1.0][4] with IPDS record: IP-139140.

## Site Files

The `map.geojson` file defines the latitude-longitude range over which the USGS computes
maps of seismic hazard from this model. The `sites.geojson` and `sites.csv` files
contain the test sites at which the USGS performs more detailed comparisons between model editions.
See the *nshmp-haz* user guide [site specification section][5] for more details on these files.

## References

**Model documentation:**  Petersen, M.D, Shumway, A.M., Powers, P.M., Mueller, C.S., Moschetti,
M.P., Frankel, A.D., Rezaeian, S., McNamara, D.E., Luco, N., Boyd, O.S., Rukstales, K.S., Jaiswal,
K.S., Thompson, E.M., Hoover, S.M., Clayton, B.S., Field, E.H., and Zeng, Y., 2020, The 2018
update of the U.S. National Seismic Hazard Model: Overview of model and implications, _Earthquake
Spectra_, 36(1) p. 5–41, doi: [10.1177/8755293019878199][6].

**Suggested citation:**  Powers, P.M. and Altekruse, J.M., 2022, nshm-conus: National Seismic
Hazard Model for the conterminous U.S. U.S. Geological Survey software release, doi:
[10.5066/P9J1OVR6][7].

[1]: https://code.usgs.gov/ghsc/nshmp/nshmp-haz
[2]: https://code.usgs.gov/ghsc/nshmp/nshmp-haz/-/blob/main/docs/README.md
[3]: https://www.usgs.gov/programs/earthquake-hazards/seismic-hazard-maps-and-site-specific-data
[4]: https://code.usgs.gov/ghsc/nshmp/nshms/nshm-conus/-/tree/5.1.0
[5]: https://code.usgs.gov/ghsc/nshmp/nshmp-haz/-/blob/main/docs/pages/Site-Specification.md
[6]: https://doi.org/10.1177/8755293019878199
[7]: https://doi.org/10.5066/P9J1OVR6
