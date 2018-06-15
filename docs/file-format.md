# The file format

This file format was created so route formats can be created easily.
It should be easy to understand for normal users.

The file format is in CSV (with , as delimiter) to be highly portable
and easy to write and read.

## Order

The order of the lines in the file is very important,
as that is how the application decides which street is first.

## Fields

1. Street name
1. Lowest number in the street
1. Highest number in the street
1. Range type (can be Even, Uneven or All)
    * For streets that only have even or uneven numbers, e.g. streets with a flat

## Restrictions and allowances

* Lowest number should be smaller or equal to the highest number
* Streets with only even numbers should have
    a lower and upper bound that's even, vice versa for uneven
* Streets can't have a comma in their name
* Streets can be in the list multiple times,
    but only when they have a different lower/upper bound or range type

## Example

```text
Example street,1,169,Uneven,LowToHigh
Example street,2,10,Even,HighToLow
Farragut,1,168,Even,LowToHigh
Killdeer,2,117,All,HighToLow
Mcguire,1,95,All,LowToHigh
Glacier Hill,2,126,All,HighToLow
```
