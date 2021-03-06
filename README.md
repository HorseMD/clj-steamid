# steamid

Conversion tool to switch between the 3 different forms of SteamID:

* steamid
* steamid64
* steamid3

Should work in both Clojure and ClojureScript projects.

## Installation

In your `project.clj` or equivalent:

```clojure
...
        :dependencies [...
                      [steamid "0.1.1"]]
                      ...
```

## Usage

Call `steamid3`, `steamid`, or `steamid64` on any steamid to get it converted to
that type.

```clojure
...:require [steamid.core :refer [steamid3 steamid steamid64]

(steamid3 "76561198060824051")
; => "[U:1:100558323]"

(steamid64 "STEAM_0:1:50279161")
; => "76561198060824051"

(steamid "[U:1:100558323]")
; => "STEAM_0:1:50279161"
```

You can check if a string is a specific SteamID:

```clojure
(steamid? "76561198060824051")
; => nil

(steamid64? "76561198060824051")
; => "76561198060824051"

(steamid3? "[U:1:100558323]")
; => "[U:1:100558323]"
```

You can also check if a string is a SteamID at all.

```clojure
(any-steamid? "bacon")
; => nil

(any-steamid? "STEAM_0:1:50279161")
; => "STEAM_0:1:50279161"
```

All SteamIDs returned are Strings.

## License

Copyright © 2016 Horse M.D.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
