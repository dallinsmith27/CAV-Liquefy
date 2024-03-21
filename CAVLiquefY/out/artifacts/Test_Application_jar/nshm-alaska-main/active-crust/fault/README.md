# Finite Fault Sources

Fault database initially included 87 fault sections.

Faults that do not have a geodetic rate, i.e. have a rate equal to `null` in the AK deformation
model, are built using the geologic rate for both `ELLIOT` and `GEO` rates, effectively giving
the geologic rate a weight of 1.0.

When loaded, the Narrow Cape fault gets a "Empty GR MFD epi branch" warning (no other faults do).

## Faults Removed

Either due to review and update of fault database and/or model simplification

- Queen Charlotte (outboard, center) [4300]
- Queen Charlotte (outboard, north) [4301]
- Queen Charlotte (outboard, south) [4302]
- Southern Queen Charlotte (Explorer interface) [4044]
- Southern Queen Charlotte (Explorer transform) [4045]
- Wasilla St. No. 1 - Needham [4260] (south of Castle Mountain)
- Pittman [4261] (south of Castle Mountain)

## Faults Merged

### Northern Alaska Range

- Northern Foothills
- Donnelly Dome
- Granite Mountain (west)
- Granite Mountain (east)
- Canteen
  - west end vertex deleted for a straighter connection with the Granite Mtn (east)
- Dot T Johnson
  - DOT T Johnson west end vertex retained to bridge the small jump from the Canteen

Northern Alaska Range faultID = 4230 for now (same as Northern Foothills)

### Kantishna Hills - Minto Flats

- Kantishna Hills (south) -- Zt/b 9-10 km, dip 5.0 S, rake 90, rate 1.2
- Kantishna Hills (north) -- Zt/b 0-15 km, dip 5.0 S, rake 90, rate 1.0 (default Zbot)
- Minto Flats (south)     -- Zt/b 0-14 km, dip 80 W, rake 0, rate 2
  - unmerging Kantishna Hills and Minto Flats (south)
  - use higher rate of Kantishna Hills (south) and depth range of the north section for combined feature
  - add projected point of intersection on Minto Flats (south)
  - retain (for now) splay on Minto Flats past north end of Kantishna hills
  - Geometry of Kantishna and Minto Flats (south) should be checked:
    - faults dip towards each other and would intersect at shallow depth
    - Kantishna Hills (north) uses default Zbot (15 km), may not be appropriate

Kantishna Hills fault ID = 4237 for now (same as the southern section)

The dip on the Kantishna Hills fault section is 5 degrees south in the geologic database. This is
below the range of valid dip values, so it is set to 10 degrees for now to satisfy the model loader.

## Faults trimmed

- Denali (Holitna) (fault ID unchanged, 4001)
- Iditarod - Nixon Fork (west, center, east) (also merged)

Merged Iditarod - Nixon Fork fault ID = 4222 (same as the western section)
