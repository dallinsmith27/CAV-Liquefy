# NSHM: Hawaiʻi

National Seismic Hazard Model (NSHM) for the State of Hawaii. This model is intended for use with
U.S. Geological Survey (USGS) hazard software [*nshmp-haz*][1].

For details on the format of this model, see the *nshmp-haz*
[user guide and implementation reference][2].

## Model Editions

* For prior model editions, see the [USGS Seismic Hazard Maps][3] web page.
* Current edition: 2021, version [2.0.0][4] with IPDS record: IP-139138.

## Model Notes

All NSHM Hawaii sources are assigned to the top-level `volcanic` tectonic setting.

## Site Files

The `map-hawaii.geojson` file defines the latitude-longitude range over which the USGS computes
maps of seismic hazard from this model. The `sites-hawaii.geojson` and `sites-hawaii.csv` files
contain the test sites at which the USGS performs more detailed comparisons between model editions.
See the *nshmp-haz* user guide [site specification section][5] for more details on these files.

## References

**Model documentation:** Petersen, M.D, Shumway, A.M., Powers, P.M., Moschetti, M.P., Llenos, A.L.,
Michael, A.J., Mueller, C.S., Frankel, A.D., Rezaeian, S., Rukstales, K.S., McNamara. D.E., Okubo,
P.G., Zeng, Y., Jaiswal, K.S., Ahdi, S.K., Altekruse, J.M., and Shiro, B.R., 2021, 2021 U.S.
National Seismic Hazard Model for the State of Hawaii, _Earthquake Spectra_, doi:
[10.1177/87552930211052061][6].

**Suggested citation:**  Powers, P.M. and Altekruse, J.M., 2022, nshm-hawaii:  National Seismic 
Hazard Model for the State of Hawaii. U.S. Geological Survey software release. doi:
[10.5066/P97SQ3AQ][7].

[1]: https://code.usgs.gov/ghsc/nshmp/nshmp-haz
[2]: https://code.usgs.gov/ghsc/nshmp/nshmp-haz/-/blob/main/docs/README.md
[3]: https://www.usgs.gov/programs/earthquake-hazards/seismic-hazard-maps-and-site-specific-data
[4]: https://code.usgs.gov/ghsc/nshmp/nshms/nshm-hawaii/-/tree/2.0.0
[5]: https://code.usgs.gov/ghsc/nshmp/nshmp-haz/-/blob/main/docs/pages/Site-Specification.md
[6]: https://doi.org/10.1177/87552930211052061
[7]: https://doi.org/10.5066/P97SQ3AQ
