## Read me first

The license of this project is LGPLv3 or later. See file src/main/resources/LICENSE for the full
text.

## What this is

This is an implementation of immutable
[Collection](http://docs.oracle.com/javase/7/docs/api/java/util/Collection.html)s written for Java 6
and above.

All these collections are backed by arrays (hence the name `abic`: Array-Backed Immutable
Collections).

The primary focus at this moment is correctness; each and every part of each contract is heavily
tested.

Right now, only `List` is fully done; however, it still lacks "proper" constructors at the moment.
This is in the plans.

