class BooksController < ApplicationController
before_action :set_book, only: [:show, :edit, :update, :destroy]

  # show the book details
  def show             
  end

  # to get index of books
  def index
  @books = Book.all
  end

  # creates a new record
  def new
  @book = Book.new
  end
   
  def create
    @book = Book.new(book_params)
    respond_to do |format|
      if @book.save
        format.html { redirect_to @book, notice: 'Book was successfully created.' }
        format.json { render json: @book.to_json, status: :created, location: @book }
      else
        format.html { render :new }
        format.json { render json: @book.errors, status: :unprocessable_entity }
      end
    end
  end
 
  def edit
  end

  def update
    respond_to do |format|
      if @book.update(book_params)
        format.html { redirect_to @book, notice: 'Book was successfully updated.' }
        format.json { render json: @book.to_json, status: :ok, location: @student }
      else
        format.html { render :edit }
        format.json { render json: @book.to_json, status: :unprocessable_entity }
      end
    end
  end

  def destroy
  raise @book.inspect
	if @book.present?
    @book.destroy
    respond_to do |format|
      format.html { redirect_to books_url, notice: 'Book was successfully destroyed.' }
      format.json { render :json => @book.to_json, :status => :unprocessable_entity } 
    end
	else 
 msg = { :status => "ok", :message => "Success!", :html => "<b>...</b>" }
    format.json  { render :json => msg } # don't do msg.to_json
     end
  end
 
    private

    def set_book
      @book = Book.find(params[:id])
    end

    def book_params
      params.require(:book).permit(:title, :author, :price)
    end
end
