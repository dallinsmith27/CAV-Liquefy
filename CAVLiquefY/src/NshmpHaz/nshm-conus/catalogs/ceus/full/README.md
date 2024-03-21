# Conterminous U.S. CEUS Full Seismicity Catalog

The full catalog for the central and eastern U.S. (CEUS) can be found in file emm_pn.c2
(file emm_pn.c2.pdf is a plot of this catalog file; blue boxes are induced seismicity zones
where induced seismicity is removed from the catalog). A description of each column in file
emm_pn.c2 is listed below:

Column 1: Mw (nonspecific momement magnitude)
Column 2: Longitude (degree east)
Column 3: Latitude (degree north)
Column 4: Depth (km)
Column 5: Year
Column 6: Month
Column 7: Day
Column 8: Hour
Column 9: Minute
Column 10: Second
Column 11: Sigma_M (Magnitude uncertainity/standard deviation of the magnitude error)
Column 12: Mwe (expcected value of Mw)
Column 13: N* (counting factor)
Column 14: Comment field


File in_ce1.geojson is the polygon that defines the collection region of input catalogs
and file in_ce2.geojson is the polygon that defines the final region of the full CEUS
catalog.

[Mueller (2019)][1] outlines the full set of catalog processing steps in further detail. 

## References

**Catalog documentation:**  Mueller, C.S., 2019, Earthquake catalogs for the USGS National 
Seismic Hazard Maps, _Seismological Research Letters_, 90(1) p. 251–261, doi: [10.1785/0220170108][1].

[1]: https://doi.org/10.1785/0220170108
