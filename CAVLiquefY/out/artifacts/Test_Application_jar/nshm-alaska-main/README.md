# NSHM: Alaska

National Seismic Hazard Model (NSHM) for the State of Alaska. This model is intended for use with
U.S. Geological Survey (USGS) hazard software [*nshmp-haz*][1].

For details on the format of this model, see the *nshmp-haz*
[user guide and implementation reference][2].

## Model Editions

* For prior model editions, see the [USGS Seismic Hazard Maps][3] web page.
* Current edition: development

## Model Notes

### Source and Logic Tree IDs

ID ranges for current and future models:

| Sources                       | ID Range    |
|:------------------------------|:-----------:|
| Crustal Faults                | 4000 - 4499 |
| Crustal Fault Systems         | 4500 - 4599 |
| Crustal Fault Zone Sources    | 4600 - 4649 |
| Crustal Gridded Seismicity    | 4650 - 4699 |
| Intraslab Gridded Seismicity  | 4700 - 4749 |
| Interface Gridded Seismicity  | 4750 - 4799 |
| Interface Sections            | 4800 - 4899 |

## Site Files

The `map.geojson` file defines the latitude-longitude range over which the USGS computes
maps of seismic hazard from this model. The `sites.geojson` and `sites.csv` files
contain the test sites at which the USGS performs more detailed comparisons between model editions.
See the *nshmp-haz* user guide [site specification section][4] for more details on these files.

## References

**Model documentation:** TBD.

**Suggested citation:**  TBD.

[1]: https://code.usgs.gov/ghsc/nshmp/nshmp-haz
[2]: https://code.usgs.gov/ghsc/nshmp/nshmp-haz/-/blob/main/docs/README.md
[3]: https://www.usgs.gov/programs/earthquake-hazards/seismic-hazard-maps-and-site-specific-data
[4]: https://code.usgs.gov/ghsc/nshmp/nshmp-haz/-/blob/main/docs/pages/Site-Specification.md
