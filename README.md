[![release](https://jitpack.io/v/umjammer/vavi-net-airplay.svg)](https://jitpack.io/#umjammer/vavi-net-airplay)


# vavi-net-airplay

 * It emulates an Airport Express for the purpose of streaming music from iTunes and compatible iPods.
 * It implements a server for the Apple RAOP protocol.
 * **Now it works as `javax.sound.sampled.spi`!**

this project is a fork of [RPlay](https://github.com/bencall/RPlay) by Benjamin de Callatay

## Installation

 * maven: https://jitpack.io/#umjammer/vavi-net-airplay
 * create key.pk8 from [sharport](https://github.com/albertz/shairport/blob/3892180dde4aefec7a97581d9beda8bee7f68fa8/shairport.c#L1156-L1178) and copy into your class path

## Thanks

 * Big thanks to David Hammerton for releasing an ALAC decoder and to soiaf for porting it to Java (https://github.com/soiaf/Java-Apple-Lossless-decoder).
 * Thanks to Jame Laird for his C implementation (shairport - https://github.com/albertz/shairport)
 * Thanks to anyone involved in one of the libraries i used for creating this software.

## Libraries & References

These libraries are included in RPlay:

 * ~~http://www.bouncycastle.org/latest_releases.html~~
 * ~~http://commons.apache.org/~~
 * https://github.com/albertz/shairport
 * https://github.com/soiaf/Java-Apple-Lossless-decoder
 * http://jmdns.sourceforge.net

## Contributors

 * [David Hammerton]
 * [James Laird]
 * [soiaf]
 * [adeward](https://github.com/adeward)
 * [jblezoray](https://github.com/jblezoray)
 * [Maik Schulz] for Mac OS X bundle (now obsolete)
 * [csholmq]
 * Everyone who has helped with shairport, the alac decoder (or the java port of it), apache commons lib or bouncycastle lib (see their README)

## TODO

 * ~~be javax.sound.sampled.spi~~
 * javax.sound.sampled.Port ???
 * [JustePort](http://nanocrew.net/software/justeport/) (WIP)
 * [jjjuste](http://www.acooke.org/jara/jjjuste/) (not tested)
 * [JAirPort](https://github.com/froks/JAirPort) (not work)