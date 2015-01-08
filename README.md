# name-maker

This is just a backup of my first time playing with clojurescript.  The result
is a "webpage" containing a maybe-unique name.

It's not by any means in a finished state.  I just want it to exist somewhere
as I might be abandoning it, and I might want to look at it at some point in
the future.

## Running this

```bash
$ lein cljsbuild once && lein ring server-headless 3000
Compiling ClojureScript.
2015-01-07 22:17:29.486:INFO:oejs.Server:jetty-7.6.8.v20121106
2015-01-07 22:17:29.592:INFO:oejs.AbstractConnector:Started SelectChannelConnector@0.0.0.0:3000
Started server on port 3000
```

Then, open your browser:

```bash
$ firefox http://localhost:3000
```

And you should see something that might look like a name.
