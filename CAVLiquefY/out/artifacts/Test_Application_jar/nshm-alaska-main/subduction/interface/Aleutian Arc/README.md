# Alaska Aleutian Subduction Zone

TODO 2023 notes

Geologic 'wide' magnitudes deprecated in favor of 'narrow' mags. Wide ruptures use
same magnitude but float down dip.

Geodetic rates for multi-section ruptures are all set to the rate derived from using
Shaw LogA+4 scaling realtion and need updating to other three scaling branches

2007 sections 4800 - 4819
2023 narrow 4820 - 4839
2023 wide 4840 - 4859

| Sections (narrow)     | ID   |
| ---------------------:|:---- |
| Yakataga              | 4820 |
| Prince William Sound  | 4821 |
| Kenai                 | 4822 |
| Kodiak                | 4823 |
| Semidi                | 4824 |
| Shumagin              | 4825 |
| Sanak                 | 4826 |
| Fox Islands           | 4827 |
| Andreanof             | 4828 |
| Adak                  | 4829 |
| Amchitka              | 4830 |
| Attu                  | 4831 |
| Komandorski           | 4832 |

| Sections (wide)       | ID   |
| ---------------------:|:---- |
| Yakataga              | 4840 |
| Prince William Sound  | 4841 |
| Kenai                 | 4842 |
| Kodiak                | 4843 |
| Semidi                | 4844 |
| Shumagin              | 4845 |
| Sanak                 | 4846 |
| Fox Islands           | 4847 |
| Andreanof             | 4848 |
| Adak                  | 4849 |
| Amchitka              | 4850 |
| Attu                  | 4851 |
| Komandorski           | 4852 |

| Sections (geodetic)   | ID   |
| ---------------------:|:---- |
| Yakataga              | 4860 |
| Prince William Sound  | 4861 |
| Kenai                 | 4862 |
| Kodiak                | 4863 |
| Semidi                | 4864 |
| Shumagin              | 4865 |
| Sanak                 | 4866 |
| Fox Islands           | 4867 |
| Andreanof             | 4868 |
| Adak                  | 4869 |
| Amchitka              | 4870 |
| Attu                  | 4871 |
| Komandorski           | 4872 |

The 2007 Alaska NSHM models earthquakes on the plate-boundary megathrust as occurring on discrete
segments and as a floating rupture along the entire arc.

In converting files first to XML, and subsequently to GeoJSON, latitude and longitude values were
rounded to 3 decimal places.

## Entire Aleutian Arc

* Original source file: `megaAll.out_revF.in`
* This geometry is not represented in the database as the entire arc can be constructed from
  individual sections.

## Segmented Model

The segmented model does not include earthquakes in the Shumagin Gap, between the
_Aleutians (west)_ and _Semidi Island_ sections. The _Aleutians (east)_ section includes the
Kodiak Island segment and extends further east towards Prince William Sound.

The megathrust segments are as follows, listed from east to west:

### Transition Zone

* ID: `4282`
* Original source file: `trans.out_revF.in`
* Assigned slip rate assumes half of the 12 mm/yr estimates based on tectonic constraints
(DeMets and Dixon, 1999) and geodetic constraints (Fletcher and Freymueller, 2003) is accomodated
as aseismic slip (Pacheco and others, 1993).
* TODO delete from mfd-map; in fault model

### Yakataga - Icy Bay

* ID: `4800`
* Original source file: `yak.out_revF.in`
* 'Icy Bay' reference came from original input file
* The Yakataga region is modeled as a flat source; given upper and lower traces defined, it does
  not violate a minimum dip
* Straight line sources reduced to endpints on conversion to GeoJSON
* Extent of region is similar to dislocation model by Sauber and others (1997, 1998) and is
  defined to emcompass a number of  poorly studied faults.

### Aleutians (east)

* ID: `4801`
* Original source file: `megaeast.out_revF.in`
* This section has been reduced (relative to teh source files from shich it was derived)
  to **not** include the Kodiak Island section for the purposes of inclusion in this database
* Depths were uniformely rounded down to 1 decimal place on conversion to XML; those depths
  with higher precision in the original input file were subsequently increased to 2 decimal
  places on conversion to GeoJSON, consistent with other segments.
* Note that this section bears the same name as a rupture in the 2007 NSHM, however the rupture
  extends further west and includes the Kodiak Island section.

### Kodiak Island

* ID: `4803`
* Original source file: `megaD.out_revF.in`
* Variable depth to bottom of rupture based on Ratchkovski and Hansen (2002) and Doser and
  others (2004).

### Semidi Island

* ID: `4804`
* Original source file: `megaB.out_revF.in`
* Variable depth to bottom of rupture based on Ratchkovski and Hansen (2002) and Doser and
  others (2004).

### Shumagin Gap

* ID: `4805`
* No earthquakes modeled for segment; covered by full-arc floater
* Section derived from entire Aleutian arc model

### Aleutians (west)

* ID: `4807`
* Original source file: `megaA.out_revF.in`

### Komandorski Island

* ID: `4812`
* Original source file: `megaF.out_revF.in`
* This source originally is referred to as the 'Far Western Aleutians' in Table 3 of the OFR,
  but is more commonly referred to as the 'Komandorski Island Region' in text and figures.
