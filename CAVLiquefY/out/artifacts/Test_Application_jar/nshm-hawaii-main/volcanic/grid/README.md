# Gridded Seismicity sources

Gridded (or smoothed) seismicity sources are modeled as uniformely distributed rupture sets
(0.02° spacing) at two discrete depths (10 km and 40 km) with a unique logic tree of GMMs for
each. Consistent with spatially varying properties of the earthquake catalog considered, source
regions are further subdivided on the basis of observed rate differences between the 'summit'
region on the Island of Hawaiʻi and everywhere else. Shallow sources outside the summit region
are further subdivided into north and south regions.

All sub-catalogs were declusterd using nearest-neighbor (`nn`) and Reasenberg (1985; `r85`)
algorithms prior to smoothing earthquake rates using a single, adaptive kernel approach.

## Gridded Rates

Rates of M = 0.0 earthquakes, `n(M=0)`, given in Table 1 of Petersen et al. (2021) are the
incremental Gutenberg-Richter a-value in
![formula](https://render.githubusercontent.com/render/math?math=n(m)\=10^{a-bm}). They are
derived from the total gridded a-value of a rupture set multiplied by a scale factor
for the catalog time period and declustering method; see grid-source `rate-tree.json`
files. Individual point source rates are the product of the total rate and the corresponding
spatial PDF value. Spatial PDFs were created by normalizing the 1960-2019 catalog a-grid file.

## Source Tree and Rupture Set IDs

| Region     | Depth           | PDF¹ | Tree ID | Rupture Set ID |
|------------|-----------------|:----:|:-------:|:--------------:|
| summit     | deep            |      | 5114    |                |
|            |                 | nn   |         | 5110           |
|            |                 | r85  |         | 5111           |
| summit     | shallow         |      | 5115    |                |
|            |                 | nn   |         | 5112           |
|            |                 | r85  |         | 5113           |
| non-summit | deep            |      | 5126    |                |
|            |                 | nn   |         | 5120           |
|            |                 | r85  |         | 5121           |
| non-summit | shallow (north) |      | 5127    |                |
|            |                 | nn   |         | 5122           |
|            |                 | r85  |         | 5123           |
| non-summit | shallow (south) |      | 5128    |                |
|            |                 | nn   |         | 5124           |
|            |                 | r85  |         | 5125           |

---
¹ Refers to the declustering model ID for the corresponding gridded seismicity spatial
  probability density function (PDF)
