# Subduction Intraslab Sources

In the 2008 NSHM, deep intraslab earthquakes in northern California, Oregon and Washington
were modeled as a single, 50 km, depth slice. In the 2014 NSHM, region dependent depth slices
were used. Early implementations kept the individual slices separate in the logic tree.
With the move to using variable depth spatial PDFs for 2023, the 2014/2018 model was refactored
to combine the depth slices.

The 2014/2018 Oregon grid is 50/50 Gaussian fixed kernel smoothing and fixed rate grid. These branches have
been combined in the OR rate files.

## Polygon Depths and IDs

| Grouping (Depth: id)   | CA                 | OR                 | WA                 |
|:---------------------- |:------------------:|:------------------:|:------------------:|
| 2008                   | 50 km: 8200        |  50 km: 8210       | 50 km: 8220        |
| 2014 Shallow           | <s>39 km: 8201</s> | <s>42 km: 8211</s> | <s>42 km: 8221</s> |
| 2014 Middle            | <s>46 km: 8202</s> | <s>50 km: 8212</s> | <s>50 km: 8222</s> |
| 2014 Deep              | <s>60 km: 8203</s> | <s>60 km: 8213</s> | <s>60 km: 8223</s> |
| 2014/2018 refactored   | variable: 8200     | variable: 8210     | variable: 8220     |

## Rupture Set IDs

Oregon and Washington intraslab MFDs are split into high and low branches, each with unique
*b*-values and corresponding *a*-values.

| State: MFD                       | Shallow     | Middle      | Deep        | var   |
|:---------------------------------|:-----------:|:-----------:|:-----------:|:-----:|
| __CA:__ M 5.0-7.2 b=0.8          | <s>8204</s> | <s>8205</s> | <s>8206</s> | 8201  |
| __OR:__ M 6.5-7.2 b=0.4 (lo)     | <s>8214</s> | <s>8216</s> | <s>8218</s> | 8211  |
| __OR:__ M 7.2-7.5,8.0 b=0.8 (hi) | <s>8215</s> | <s>8217</s> | <s>8219</s> | 8212  |
| __WA:__ M 5.0-7.2 b=0.4  (lo)    | <s>8224</s> | <s>8226</s> | <s>8228</s> | 8221  |
| __WA:__ M 7.2-7.5,8.0 b=0.8 (hi) | <s>8225</s> | <s>8227</s> | <s>8229</s> | 8222  |

## Source Tree IDs

__CA:__ 8230  
__OR:__ 8231  
__WA:__ 8232  
