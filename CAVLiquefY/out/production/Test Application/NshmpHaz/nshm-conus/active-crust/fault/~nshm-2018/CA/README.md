# California - UCERF3

This directory contains the Uniform California Earthquake Rupture Forecast, version 3 (UCERF3)
logic tree with two branches, one for each fault model considered. Each branch contains the data
for a 'branch-averaged' fault system solution.

To avoid duplicate IDs in the hazard model, fault system subsections for each fault
model have values at 310000 and 320000, respectively. The corresponding fault system rupture
sets are assigned IDs of 8300 and 8301. Each subsection also preserves an index used for
stitching subsections into ruptures. The UCERF3 logic tree as a whole has the ID: `8309`.
