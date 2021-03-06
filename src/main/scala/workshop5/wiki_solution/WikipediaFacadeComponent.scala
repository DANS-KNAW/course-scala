/**
 * Copyright (C) 2016 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package workshop5.wiki_solution

import rx.lang.scala.Observable

import scala.io.Source

trait WikipediaFacadeComponent {

  val wikipediaFacade: WikipediaFacade

  trait WikipediaFacade {
    val baseUrl: String

    def search(word: String): Observable[String] = Observable.defer {
      val url = baseUrl + word.replace(" ", "%20")

      Observable.using(Source.fromURL(url))(r => Observable.just(r.mkString), _.close(), disposeEagerly = true)
    }
  }
}
